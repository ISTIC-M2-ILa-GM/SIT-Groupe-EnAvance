import unittest
from scripts.common import *


class TestCommon(unittest.TestCase):

    # les tests doivent commence par "test"
    def setUp(self):
        pass

    def test_get_request(self):
        res_get_request = get_request("http://httpbin.org/get")
        self.assertEqual(res_get_request["url"], "http://httpbin.org/get")

    def test_post_request(self):
        body = [{"hello": "world"}, {"hello2": "world2"}]
        res_post_request = post_request("http://httpbin.org/post", body)
        self.assertEqual(res_post_request["json"], body)


if __name__ == '__main__':
    unittest.main()
