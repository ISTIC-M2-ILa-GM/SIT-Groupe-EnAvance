import os


def take_screenshot():
    screen = "temp/screenshot.png"
    command = "import -window root "+screen
    os.system(command)
    print(command)
    return screen


if __name__ == '__main__':
    take_screenshot()
