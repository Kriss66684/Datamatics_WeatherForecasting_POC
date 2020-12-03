package com.dm.bdd.integrations.report_utils;

import java.io.File;
import java.util.Date;

import com.dm.bdd.integrations.common_utils.ConfigReader;

import ws.schild.jave.AudioAttributes;
import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.EncodingAttributes;
import ws.schild.jave.InputFormatException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.VideoAttributes;

/***
 * 
 * @author 
 * This is a Utility Class which converts the .AVI format video to .MP4 format
 * It extends the BaseClass to initialize the Log4j Class to be used in this class
 * Log4j is used to log the necessary information during the process of script execution
 * 
 */
public class VideoConverter
{

	/***
	 * 
	 * Method's utility is to convert .AVI format video to .MP4 format
	 * AVI video is not executable by Media Player, only MP4
	 * Requires VLC Media Player installation.
	 * throws no Exception
	 * @param no parameter is passed
	 * @return the method does not return any value
	 * 
	 */
	public static void convertVideoAVIToMP4() 
	{

		String inputFilename = ConfigReader.getValue("videoInputPath");
		String outputFilename = ConfigReader.getValue("videoOutputPath");
		File source = new File(inputFilename);
		File target = new File(outputFilename);
		System.out.println( "pre-conversion path:" + inputFilename);
		System.out.println( "converted path:" + outputFilename);
		AudioAttributes audio = new AudioAttributes(); 
		audio.setCodec ( "libmp3lame"); // audio coding format
		audio.setBitRate(new Integer(800000));
		audio.setChannels(new Integer(1)); 
		//audio.setSamplingRate(new Integer(22050)); 
		VideoAttributes video = new VideoAttributes(); 
		video.setCodec ("mpeg4"); // video encoding format
		video.setBitRate(new Integer(3200000));
		video.setFrameRate (new Integer (15)); // small digital set, the video will Caton
		EncodingAttributes attrs = new EncodingAttributes(); 
		attrs.setFormat("mp4");
		attrs.setAudioAttributes(audio); 
		attrs.setVideoAttributes(video); 
		Encoder encoder = new Encoder();  
		MultimediaObject multimediaObject = new MultimediaObject(source);
		try 
		{
			System.out.println( "avi conversion start switch MP4 ---:" + new Date ());
			encoder.encode(multimediaObject, target, attrs);
			System.out.println( "avi switch MP4 --- End Conversion:" + new Date ());
		}
		catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InputFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EncoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}


}
