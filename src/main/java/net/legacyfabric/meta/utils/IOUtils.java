package net.legacyfabric.meta.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class IOUtils {
	public static void emptyDir(Path dir) {
		try {
			if (Files.exists(dir)) {
				Files.walk(dir)
						.filter(Files::isRegularFile)
						.forEach(file -> {
							try {
								Files.delete(file);
							} catch (IOException e) {
								e.printStackTrace();
							}
						});
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
