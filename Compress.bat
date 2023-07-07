@echo off
for /R "%~dp0Official" %%f in (*.png) do (
    pushd "%%~dpf"
    if not exist "%%~nf.webp" (
        cwebp -progress -mt -noalpha -q 90 -m 6 -af "%%~nxf" -o "%%~nf.webp"
        echo.
    )
    popd
)
for /R "%~dp0Workshop" %%f in (*.png) do (
    pushd "%%~dpf"
    if not exist "%%~nf.webp" (
        cwebp -progress -mt -noalpha -q 90 -m 6 -af "%%~nxf" -o "%%~nf.webp"
        echo.
    )
    popd
)