import os
import datetime
import shutil

if __name__ == "__main__":
    todayDate = datetime.datetime.today().strftime("%Y-%m-%d")

    try:
        os.mkdir(todayDate)
    except OSError:
        print("Error: Cannot create the directory '{}'".format(todayDate))
        exit(-1)
    
    try:
        os.mkdir(todayDate + '/Assets')
    except OSError:
        print("Error: Cannot create the directory '{}'".format(todayDate))
        exit(-1)
    
    for i in range(1, 6):
        try:
            fileName = f"{todayDate}/{todayDate}-{i}-제목.md"
            shutil.copy("template.md", fileName)
        except:
            print("Error: Cannot copy the template as '{}'".format(fileName))
            exit(-1)
    
    print("모든 작업이 잘 완료되었습니다.")