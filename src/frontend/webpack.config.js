const path = require('path');
const webpack = require('webpack');

const config = {
    entry  : {
        regular_user: './js/regular_user.js',
        admin_user  : './js/admin_user.js',
    },
    output : {
        path    : path.resolve(__dirname, '../main/resources/static/js'),
        filename: '[name].bundle.js',
        clean   : true,
    },
    module : {
        rules: [
            {
                test   : /\.m?js$/,
                exclude: /(node_modules|bower_components)/,
                use    : {
                    loader : 'babel-loader',
                    options: {
                        presets: ['@babel/preset-env']
                    }
                }
            },
            {
                test: /\.css$/i,
                use : ["style-loader", "css-loader"],
            }
        ]
    },
    plugins: [
        new webpack.ProvidePlugin({
            $     : "jquery",
            jQuery: "jquery"
        })
    ]
};

module.exports = config;