var webpack = require("webpack");
var path = require("path");
var pkg = require("./package.json");

var targetDir = path.join(__dirname, pkg.config.targetDir);

module.exports = {
    entry: {
            path: targetDir
    },
	output: {
		path: targetDir,
		filename: "bundle.js"
	}
 };
