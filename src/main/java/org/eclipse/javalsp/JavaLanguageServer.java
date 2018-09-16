/*******************************************************************************
 * Copyright (c) 2018 BlackBerry QNX and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.eclipse.javalsp;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CompletableFuture;

import org.eclipse.lsp4j.InitializeParams;
import org.eclipse.lsp4j.InitializeResult;
import org.eclipse.lsp4j.MessageParams;
import org.eclipse.lsp4j.MessageType;
import org.eclipse.lsp4j.ServerCapabilities;
import org.eclipse.lsp4j.jsonrpc.Launcher;
import org.eclipse.lsp4j.launch.LSPLauncher;
import org.eclipse.lsp4j.services.LanguageClient;
import org.eclipse.lsp4j.services.LanguageClientAware;
import org.eclipse.lsp4j.services.LanguageServer;
import org.eclipse.lsp4j.services.TextDocumentService;
import org.eclipse.lsp4j.services.WorkspaceService;

public class JavaLanguageServer implements LanguageServer, LanguageClientAware {

	private JavaTextDocumentService textDocumentService = new JavaTextDocumentService();
	private JavaWorkspaceService workspaceService = new JavaWorkspaceService();
	private LanguageClient client;

	public static void main(String[] args) throws Exception {
		try (ServerSocket socketServer = new ServerSocket(6565)) {
			Socket socket = socketServer.accept();
			JavaLanguageServer server = new JavaLanguageServer();
			Launcher<LanguageClient> launcher = LSPLauncher.createServerLauncher(server,
					socket.getInputStream(),
					socket.getOutputStream());
			LanguageClient client = launcher.getRemoteProxy();
			server.connect(client);
			launcher.startListening();
		}
	}

	@Override
	public CompletableFuture<InitializeResult> initialize(InitializeParams params) {
		ServerCapabilities caps = new ServerCapabilities();
		InitializeResult result = new InitializeResult(caps);
		CompletableFuture<InitializeResult> future = new CompletableFuture<InitializeResult>();
		future.complete(result);
		return future;
	}

	@Override
	public CompletableFuture<Object> shutdown() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void exit() {
		// TODO Auto-generated method stub

	}

	@Override
	public TextDocumentService getTextDocumentService() {
		return textDocumentService;
	}

	@Override
	public WorkspaceService getWorkspaceService() {
		return workspaceService;
	}

	@Override
	public void connect(LanguageClient client) {
		this.client = client;
		this.client.logMessage(new MessageParams(MessageType.Info, "java-lsp started"));
	}
}
