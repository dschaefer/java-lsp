import * as net from 'net';

process.stdin.setEncoding('utf8');

const client = new net.Socket();
client.connect(6565, '127.0.0.1', function() {
    client.pipe(process.stdout);
    process.stdin.pipe(client);
});

process.stdin.on('end', () => {
  process.exit();
});