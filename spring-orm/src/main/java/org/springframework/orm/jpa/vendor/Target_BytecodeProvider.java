/*
 * Copyright 2002-2024 the original author or authors.
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

package org.springframework.orm.jpa.vendor;

import java.util.Map;
import java.util.function.Predicate;

import com.oracle.svm.core.annotate.Substitute;
import com.oracle.svm.core.annotate.TargetClass;
import org.hibernate.bytecode.spi.ReflectionOptimizer;
import org.hibernate.property.access.spi.PropertyAccess;

/**
 * Hibernate 6.3+ substitution designed to leniently return {@code null}, as authorized by the API, to avoid throwing an
 * {@code HibernateException}.
 *
 * @author Sebastien Deleuze
 * @since 6.1
 * @see <a href="https://hibernate.atlassian.net/browse/HHH-17568">HHH-17568</a>
 */
@TargetClass(className = "org.hibernate.bytecode.internal.none.BytecodeProviderImpl", onlyWith = Target_BytecodeProvider.SubstituteOnlyIfPresent.class)
final class Target_BytecodeProvider {

	@Substitute
	public ReflectionOptimizer getReflectionOptimizer(Class<?> clazz, Map<String, PropertyAccess> propertyAccessMap) {
		return null;
	}

	static class SubstituteOnlyIfPresent implements Predicate<String> {

		@Override
		public boolean test(String type) {
			try {
				Class<?> clazz = Class.forName(type, false, getClass().getClassLoader());
				clazz.getDeclaredMethod("getReflectionOptimizer", Class.class, Map.class);
				return true;
			}
			catch (ClassNotFoundException | NoClassDefFoundError | NoSuchMethodException ex) {
				return false;
			}
		}
	}

}
