/*
 * Copyright 2002-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.core;

/**
 * Default implementation of the {@link ParameterNameDiscoverer} strategy interface,
 * using the Java 8 standard reflection mechanism, and falling back to the ASM-based
 * {@link LocalVariableTableParameterNameDiscoverer} for checking debug information
 * in the class file (e.g. for classes compiled with earlier Java versions).
 *
 * <p>If a Kotlin reflection implementation is present,
 * {@link KotlinReflectionParameterNameDiscoverer} is added first in the list and
 * used for Kotlin classes and interfaces. When compiling or running as a GraalVM
 * native image, the {@code KotlinReflectionParameterNameDiscoverer} is not used.
 *
 * <p>Further discoverers may be added through {@link #addDiscoverer(ParameterNameDiscoverer)}.
 *
 * @author Juergen Hoeller
 * @author Sebastien Deleuze
 * @author Sam Brannen
 * @since 4.0
 * @see StandardReflectionParameterNameDiscoverer
 * @see LocalVariableTableParameterNameDiscoverer
 * @see KotlinReflectionParameterNameDiscoverer
 */
public class DefaultParameterNameDiscoverer extends PrioritizedParameterNameDiscoverer {

	public DefaultParameterNameDiscoverer() {
		// TODO Remove this conditional inclusion when upgrading to Kotlin 1.5, see https://youtrack.jetbrains.com/issue/KT-44594
		if (KotlinDetector.isKotlinReflectPresent() && !NativeDetector.inNativeImage()) {
			addDiscoverer(new KotlinReflectionParameterNameDiscoverer());
		}
		// 使用discover去查找我们的method里面参数的名称
		// standard是通过，当我们编译的时候在编译里面设置-parameter参数的方式，我们通过java提供的method.getParameters里面能拿到参数名称
		// 但并不是所有的项目打包都会增加这个参数配置
		addDiscoverer(new StandardReflectionParameterNameDiscoverer());

		// 所以还需要用下面这个方法，这个方法就是在我们编译的class文件中，每个项目都有一个局部变量表，在里面可以拿到具体的方法参数名称，可以看show bytecode
		addDiscoverer(new LocalVariableTableParameterNameDiscoverer());
	}

}
