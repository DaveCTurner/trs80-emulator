#!/bin/bash

head -n$1 amazing.bas > amazing-test.bas
java -cp bin:lib/beaver.jar dct25.trs80.ConvertToJava amazing-test.bas
