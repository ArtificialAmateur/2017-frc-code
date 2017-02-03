#!/bin/bash

cd /home/artificial/docs/projects/fun
ls | xargs -I{} git -C {} pull

cd /home/artificial/docs/projects/cyberpatriot
ls | xargs -I{} git -C {} pull

cd /home/artificial/docs/projects/robotics
ls | xargs -I{} git -C {} pull
