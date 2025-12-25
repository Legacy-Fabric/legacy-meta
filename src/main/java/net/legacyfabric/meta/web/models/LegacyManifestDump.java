package net.legacyfabric.meta.web.models;

import java.util.List;

public record LegacyManifestDump(List<Version> versions) {
	public static record Version(String id, String type, String url, String time, String releaseTime) {}
}
