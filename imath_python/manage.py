# coding:UTF-8
from ihome import create_app, db
from flask_script import Manager
from flask_migrate import Migrate,MigrateCommand
# 创建flask的应用对象
app = create_app("develop")
manager = Manager(app)
Migrate(app, db)  #迁移脚本
manager.add_command("db", MigrateCommand)

if __name__ == '__main__':
    manager.run()