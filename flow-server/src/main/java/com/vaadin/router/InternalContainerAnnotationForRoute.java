/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.router;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation enabling using multiple {@link RouteAlias @RouteAlias}
 * annotations.
 * <p>
 * <b>NOT meant to be used directly</b>, for multiple style sheet dependencies,
 * {@link RouteAlias @RouteAlias} should be used instead.
 *
 * @author Vaadin Ltd
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Inherited
public @interface InternalContainerAnnotationForRoute {

    /**
     * Not to be used, instead multiple {@link RouteAlias @RouteAlias}
     * annotations should be used.
     *
     * @return an array of the RouteAlias annotations
     */
    RouteAlias[] value();
}
