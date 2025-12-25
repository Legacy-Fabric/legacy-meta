package net.legacyfabric.meta.web.models;

import net.fabricmc.meta.web.models.LegacyDbDump;

public class LFLegacyDbDump extends LegacyDbDump {
	public final LegacyManifestDump launcherMeta;

	public LFLegacyDbDump(LegacyDbDump dbDump, LegacyManifestDump launcherMeta) {
		super(dbDump.game, dbDump.mappings, dbDump.intermediary, dbDump.loader, dbDump.installer);
		this.launcherMeta = launcherMeta;
	}
}
