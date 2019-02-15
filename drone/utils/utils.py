import base64


def image_to_b64(file_path):
    with open(file_path, "rb") as image_file:
        encoded_string = base64.b64encode(image_file.read())

    return encoded_string
