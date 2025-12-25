/*
 * Copyright (c) 2025 Legacy Fabric
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.legacyfabric.meta.web;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.fabricmc.meta.FabricMeta;

import net.legacyfabric.meta.utils.LWJGLVersions;
import net.legacyfabric.meta.utils.LegacyReference;

public class ProfileHelper {
	public static JsonArray enrichProfile(String version) {
		var array = new JsonArray();

		var versionManifest = LegacyEndpointsV2.getVersionManifest(version);

		if (versionManifest != null) {
			JsonObject manifest = FabricMeta.GSON.fromJson(versionManifest, JsonObject.class);

			boolean lwjgl2Present = false;

			for (var lib : manifest.getAsJsonArray("libraries")) {
				var obj = lib.getAsJsonObject();
				var name = obj.get("name").getAsString();

				if (name.startsWith("org.ow2.asm:asm-all")) {
					var newObj = new JsonObject();
					newObj.addProperty("name", obj.get("name").getAsString());
					var rules = new JsonArray();
					var rule = new JsonObject();
					rule.addProperty("action", "disallow");
					rules.add(rule);
					newObj.add("rules", rules);
					array.add(newObj);
				} else if (name.startsWith("org.lwjgl.lwjgl:lwjgl:2")) {
					lwjgl2Present = true;
				}
			}

			if (lwjgl2Present) {
				JsonObject mainObject = new JsonObject();
				mainObject.addProperty("name", "org.lwjgl.lwjgl:lwjgl:" + LWJGLVersions.LWJGL2.version);
				mainObject.addProperty("url", LegacyReference.LEGACY_FABRIC_MAVEN_URL);
				array.add(mainObject);

				JsonObject utilObject = new JsonObject();
				utilObject.addProperty("name", "org.lwjgl.lwjgl:lwjgl_util:" + LWJGLVersions.LWJGL2.version);
				utilObject.addProperty("url", LegacyReference.LEGACY_FABRIC_MAVEN_URL);
				array.add(utilObject);

				JsonObject platformObject = new JsonObject();
				platformObject.addProperty("name", "org.lwjgl.lwjgl:lwjgl-platform:" + LWJGLVersions.LWJGL2.version);
				platformObject.addProperty("url", LegacyReference.LEGACY_FABRIC_MAVEN_URL);
				JsonObject extract = new JsonObject();
				JsonArray excludes = new JsonArray();
				excludes.add("META-INF/");
				extract.add("exclude", excludes);
				platformObject.add("extract", extract);
				JsonObject natives = new JsonObject();
				natives.addProperty("linux", "natives-linux");
				natives.addProperty("osx", "natives-osx");
				natives.addProperty("windows", "natives-windows");
				platformObject.add("natives", natives);
				array.add(platformObject);
			}
		}

		return array;
	}
}
