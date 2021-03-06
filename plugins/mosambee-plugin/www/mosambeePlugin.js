var exec = require('cordova/exec');
function CordovaPlugin(){ }

CordovaPlugin.prototype.initMosambeePayment = function(successCallback,errorCallback){
  cordova.exec(successCallback,errorCallback,'MosambeePlugin','initMosambeePayment',[]);
}
CordovaPlugin.prototype.startMosambeePayment = function(successCallback,errorCallback){
  cordova.exec(successCallback,errorCallback,'MosambeePlugin','startMosambeePayment',[]);
}
CordovaPlugin.prototype.onRequestPermissionsResult = function(requestCode, permissions, grantResults,successCallback,errorCallback){
  var options = {};
  options.requestCode = requestCode;
  cordova.exec(successCallback,errorCallback,'MosambeePlugin','onRequestPermissionsResult',[options]);
}
CordovaPlugin.prototype.onResult = function(final ResultData result,successCallback,errorCallback){
  var options = {};
  options.result = result;
  cordova.exec(successCallback,errorCallback,'MosambeePlugin','onResult',[options]);
}
CordovaPlugin.prototype.onCommand = function(final String command,successCallback,errorCallback){
  var options={};
  options.command = command;
  cordova.exec(successCallback,errorCallback,'MosambeePlugin','onCommand',[options]);
}

CordovaPlugin.install = function() {
  if (!window.plugins) {
    window.plugins = {};
  }
  window.plugins.CordovaPlugin = new CordovaPlugin();
  return window.plugins.CordovaPlugin;
};
cordova.addConstructor(CordovaPlugin.install);
