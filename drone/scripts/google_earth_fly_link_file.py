#!/usr/bin/env python3
# -*- coding: utf-8 -*-


kml_template = '''<?xml version="1.0" encoding="UTF-8"?>
<kml xmlns="http://www.opengis.net/kml/2.2">
  <Document>
    <name>Test camera KML</name>
    <open>1</open>
    <Camera>
      <longitude>{longitude}</longitude>
      <latitude>{latitude}</latitude>
      <altitude>{altitude}</altitude>
      <heading>{heading}</heading>
      <tilt>{tilt}</tilt>
      <altitudeMode>absolute</altitudeMode>
    </Camera>
  </Document>
</kml>'''


def write_coord_xml(lat, lon, alt):
    coord = {'longitude': lon,
             'latitude': lat,
             'altitude': alt,
             'heading': 160.0 + 80,
             'tilt': 90.0}
    with open("temp/camera_tmp.kml", "w") as kml_file:
        kml_file.write(kml_template.format(**coord))

