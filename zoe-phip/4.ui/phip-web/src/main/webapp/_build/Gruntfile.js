module.exports = function (grunt) {

    grunt.initConfig({
        pkg: grunt.file.readJSON('package.json'),
        meta: {
            imagePath: '../assets/images/',
            srcImages: '../assets/src/images/',
            distImages: '../assets/dist/Images/',
            stylePath: '../assets/style/',
            srcStyle: '../assets/src/Style/',
            distStyle: '../assets/dist/style/',
            scriptPath: '../assets/script/',
            srcScript: '../assets/src/script/',
            distScript: '../assets/dist/script/'
        },
        //图片压缩
        imagemin: {                          // Task
            dynamic: {                         // Another target
                files: [{
                    expand: true,                  // Enable dynamic expansion
                    cwd: '<%= meta.srcImages %>',  // Src matches are relative to this path
                    src: ['**/*.{png,jpg,gif}'],   // Actual patterns to match
                    dest: '<%= meta.distImages %>' // Destination path prefix
                }]
            }
        },
        //scss编译
        sass: {
            dist: {
                files: [{
                    expand: true,
                    cwd: '<%= meta.srcStyle %>',
                    src: ['**/*.scss'],
                    dest: '<%= meta.distStyle %>',
                    ext: '.css'
                }]
            }
        },
        //css压缩
        cssmin: {
            target: {
                files: [{
                    expand: true,
                    cwd: '<%= meta.distStyle %>',
                    src: ['**/*.css'],
                    dest: '<%= meta.distStyle %>',
                    ext: '.min.css'
                }]
            }
        },
        // seajs模块编译
        transport: {
            options: {
                // 模块默认前缀，配合seajs的config.js中的config > paths > modules的对应值
                // 并且在实际引用时，需与use中的前缀一致
                idleading: 'modules/'
            },
            build: {
                files: [
                    {
                        expand: true,
                        cwd: '<%= meta.srcScript %>',
                        src: ['**/*.js'],
                        dest: '<%= meta.distScript %>',
                        ext: '.js'
                    }
                ]
            }
        },
        //js压缩功能
        uglify: {
            compress: {
                files: [{
                    expand: true,
                    cwd: '<%= meta.distScript %>',
                    src: ['**/*.js', '!**/*-debug.js'],
                    dest: '<%= meta.distScript %>',
                    ext: '.js'
                }]
            }
        },
        // 清除临时文件
        clean: {
            options: {
                force: true
            },
            build: {
                src: ["<%= meta.distScript %>**/*-debug.js", "<%= meta.distStyle %>**/*.min.css", "<%= meta.distStyle %>*.min.css"]
            }
        },
        //监视功能
        //watch: {
        //    css: {
        //        files: ['<%= meta.srcStyle %>/**/*.scss'],
        //        task: ['imagemin', 'sass', 'cssmin']
        //    }
        //}
        // 监视功能
        watch: {
            script: {
                files: [
                    '<%= meta.srcScript %>**/*.js'
                ],
                tasks: ['uglify:debug']
            },
            css: {
                files: [
                    '<%= meta.srcStyle %>**/*.scss'
                ],
                tasks: ['clean','imagemin', 'sass', 'cssmin']
            }
        }
    });
    // 加载

    grunt.loadNpmTasks('grunt-contrib-imagemin');//压缩图片
    grunt.loadNpmTasks('grunt-contrib-sass'); // sass解析模块
    grunt.loadNpmTasks('grunt-contrib-cssmin');//css压缩
    grunt.loadNpmTasks('grunt-cmd-transport'); // seajs模块编译
    grunt.loadNpmTasks('grunt-contrib-uglify'); // js压缩模块
    grunt.loadNpmTasks('grunt-contrib-watch');
    grunt.loadNpmTasks('grunt-contrib-clean');

    grunt.registerTask('clear', ['clean']);
    grunt.registerTask('buildcss', ['imagemin', 'sass', 'cssmin']);
    grunt.registerTask('buildjs', ["transport", "uglify"]);

    // 版本发布任务
    grunt.registerTask('release', function () {
        grunt.task.run('buildjs');
        grunt.task.run('clear');
        grunt.task.run('buildcss');
    });
    grunt.registerTask('watch-css', ['watch:css']);
    // 默认任务
    grunt.registerTask('default', ['release']);

}