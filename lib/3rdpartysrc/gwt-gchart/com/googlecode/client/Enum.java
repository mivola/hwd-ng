/**
 * 
 * The MIT License
 * 
 * Copyright (c) 2007 the original author or authors.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:

 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.

 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.googlecode.client;

/**
 * The Enum class helper to emulate the enum keyword in java 1.4. Code snipet
 * found on WikiCodia
 * 
 * @author Silvin Lubecki
 */

import com.google.gwt.user.client.rpc.IsSerializable;
import java.util.Map;
import java.util.HashMap;

public class Enum implements IsSerializable {
    private /* final */ String name;
    private /* final */ int ordinal;

    public Enum() {
        this("error", -1);
    }

    protected Enum(String name, int ordinal) {
        this.name = name;
        this.ordinal = ordinal;
    }

    public final String getName() {
        return name;
    }

    public final int getOrdinal() {
        return ordinal;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        Enum anEnum = (Enum) o;

        if (!name.equals(anEnum.name) || ordinal != anEnum.ordinal)
          return false;

        return true;
    }

    public int hashCode() {
        return ordinal;
    }

    protected static Map makeValuesMap(Enum[] enums) {
        Map result = new HashMap(enums.length);
        for (int i = 0; i < enums.length; i++) {
            Enum anEnum = enums[i];
            result.put(anEnum.getName(), anEnum);
        }

        return result;
    }
}