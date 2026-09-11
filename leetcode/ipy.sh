#!/bin/bash

pbpaste | cat > ${1:-solution.py}
