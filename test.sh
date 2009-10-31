#!/bin/bash

head -n$1 amazing.bas > amazing-test.bas
java -cp bin:lib/beaver.jar:/usr/lib/eclipse/plugins/org.eclipse.jdt.core_3.2.3.v_686_R32x.jar:/usr/lib/eclipse/plugins/org.eclipse.equinox.common_3.2.0.v20060603.jar dct25.trs80.ConvertToJava amazing-test.bas
