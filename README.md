# java-lsp
A language server for Java based on ecj and select JDT packages and uses maven and gradle for build info

This is really just an experiment to see if you could build a Java language server just using ecj
and getting build information from the maven and gradle build models. We'll look to JDT for other
functionality we may need like code completion and indexing.

This differs from other approaches in that it skips Eclipse's OSGi plug-in model and the IResource
project system.

The main use case is for Android and maybe Java microservices for those who prefer to use lighter weight
IDEs like VS code.

It's also a vehicle that allows me to become more familiar with LSP and keep my Java chops fresh.

## Build

The root directory is an Eclipse Gradle project. The vscode directory is a small extension that
hooks up the language server.

## Launch

Launch the JavaLanguageServer. It currently creates a socket server on port 6565.
On the vscode side, there's a language server written in node which pipes stdin/stdout to the socket. 