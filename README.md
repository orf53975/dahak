# dahak
This is an exploit kit for restricted secured computer systems that contain a JRE or Microsoft App-V. It is recommended to deploy the payload using a LanSchool console or SMB exploit. Use Powershell to configure properties to execute the JAR file. (i.e. add JAR file associations to run w/ javaw.exe if using App-V to install JAVA.
     
# Installation for restricted systems with App-V
To install a package, run the following commands in a PowerShell script
Mount-AppvClientPackage -PackageId packageid -VersionId versionid
For example, to install eclipse, which is bundled with a JRE, run:
Mount-AppvClientPackage -PackageId C87BE6C9-9379-431A-AE70-FD15E8F1AACA -VersionId BBA5E63B-FA99-4CF9-9FA8-1991602E58BC
If file associations are not possible for JAR files, try using the included game.psc1 script in the startup folder, and remove the autocopy start function.

# License
Copyright (C) 2017 _c0da_ (Victor Du)

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
