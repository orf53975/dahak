# artemidorius-keylogger
A cross-platform program written in JAVA to log keystrokes and save them to a file. 

# How to use
1. Compile the following code into a JAR file
2. Create a folder in the C drive called "ClassPolicy" 
3. All logged keystrokes will be logged to a file named [[username of target]].txt
RECOMMENDED: Use JSmooth to wrap the finished JAR in an EXE, then assign a trusted Icon to it.

# Usage and Disclaimer
This program is strictly for educational usage only, and I am not responsible for any damages, financial, legal or otherwise you incur by misuse of this program. By using this program. you agree to use this in a responsible, ethical and legal manner. If you want to be a villain number one with this program, you are responsible for any damages you cause with it, and also have to catch a superhero on the run. This program can be used legally for purposes, such as:
- Workplace Management
- School Student Monitoring
- Parental Control Monitoring
- Experimentation

# Updates
- v0.2b: Reliability updates, bug fixes, text formatting.
- v0.2.1b: Added automated log file with username, hide in known trusted folder
- v0.3b: Added startup persistance module, prevent taskkill /im javaw.exe /f from killing all logger processes
- v0.4b: Added automatic timestamp to write system session startup time, mild implementation of Command/Control.
- UPCOMING: 0.4.1b: Currently under development, will automatically attempt sending log file over network each 15 minutes to a user-defined IP address. PM me if you would like to help with development.

# License
Licensed under GNU GPL v3
