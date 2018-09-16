import * as path from 'path';
import * as vscode from 'vscode';
import { NodeModule, LanguageClientOptions, LanguageClient } from 'vscode-languageclient';

export function activate(context: vscode.ExtensionContext) {
    const serverOptions : NodeModule = {
        module: path.join(context.extensionPath, 'out', 'server.js')
    }

    const clientOptions: LanguageClientOptions = {
        documentSelector: [
            { scheme: 'file', language: 'java' }
        ]
    };

    context.subscriptions.push(
        new LanguageClient('java-lsp', "Java LSP", serverOptions, clientOptions).start()
    );
}

export function deactivate() {
}