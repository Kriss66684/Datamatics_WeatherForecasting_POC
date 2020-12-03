package com.dm.bdd.integrations.report_utils;

import java.io.File;

public class DeleteVideoPath {
	public static void deleteVideo() {

		File folder = new File("video");

		for (File file : folder.listFiles()) {

			if (file.getName().endsWith(".mp4") || file.getName().endsWith(".avi")) {
				if (file.delete()) {
					System.out.println(file.getName() + " File deleted successfully");
				} else {
					System.out.println("Failed to delete the file");
				}
			} else {

			}
		}
	}
}
