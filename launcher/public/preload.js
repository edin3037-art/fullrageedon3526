const { contextBridge, ipcMain } = require('electron');

contextBridge.exposeInMainWorld('api', {
  launchGame: (version) => ipcMain.invoke('launch-game', version),
  getAccounts: () => ipcMain.invoke('get-accounts'),
  sendMessage: (channel, data) => ipcMain.send(channel, data),
  onMessage: (channel, func) => ipcMain.on(channel, (event, ...args) => func(...args))
});
