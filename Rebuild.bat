@echo off
pushd "%~dp0"
javac *.java && java WebBuilder
popd