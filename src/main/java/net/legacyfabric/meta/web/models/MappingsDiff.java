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

package net.legacyfabric.meta.web.models;

import java.util.ArrayList;
import java.util.List;

public class MappingsDiff {
	public final List<Entry> classes = new ArrayList<>();
	public final List<ClassEntry> fields = new ArrayList<>();
	public final List<ClassEntry> methods = new ArrayList<>();

	public record Entry(String source, String target) {}
	public record ClassEntry(String owner, String source, String target, String sourceDesc, String targetDesc) {}
}
