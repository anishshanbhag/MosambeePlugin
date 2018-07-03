cordova.define('cordova/plugin_list', function(require, exports, module) {
module.exports = [
  {
    "id": "mosambee-plugin.mosambeePlugin",
    "file": "plugins/mosambee-plugin/www/mosambeePlugin.js",
    "pluginId": "mosambee-plugin",
    "clobbers": [
      "window.plugins.mosambeePlugin"
    ]
  }
];
module.exports.metadata = 
// TOP OF METADATA
{
  "mosambee-plugin": "0.0.1"
};
// BOTTOM OF METADATA
});