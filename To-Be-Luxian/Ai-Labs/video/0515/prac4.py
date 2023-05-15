# 소벨 필터링

import cv2;
import numpy as np;

image = cv2.imread('image.jpg', -1)[0:500, 100:500, :]

gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
gauss = cv2.GaussianBlur(gray, (3, 3), 3)

# 소벨 필터링
kernel = np.ones((5, 5), dtype=np.uint8)

x_edge = cv2.Sobel(gauss, cv2.CV_32F, 1, 0, 3, 1) # CV_32S: 32비트 영역으로 늘려 연산 / dx=1 dy=0: x축에 대해서만 연산 
x_edge = cv2.convertScaleAbs(x_edge) # 32비트 영역에서 연산했던 것을 다시 0-255 범위로 환산

y_edge = cv2.Sobel(gauss, cv2.CV_32F, 1, 0, 3, 1) # CV_32S: 32비트 영역으로 늘려 연산 / dx=1 dy=0: x축에 대해서만 연산 
y_edge = cv2.convertScaleAbs(y_edge) # 32비트 영역에서 연산했던 것을 다시 0-255 범위로 환산

whole_edge = cv2.Sobel(gauss, cv2.CV_32F, 1, 1, 3, 1) # CV_32S: 32비트 영역으로 늘려 연산 / dx=1 dy=0: x축에 대해서만 연산 
whole_edge = cv2.convertScaleAbs(whole_edge) # 32비트 영역에서 연산했던 것을 다시 0-255 범위로 환산

# 출력하여 확인
cv2.imshow('ORIGINAL (BLUR)', gauss)
cv2.imshow('SOBEL (DX ONLY)', x_edge)
cv2.imshow('SOBEL (DY ONLY)', y_edge)
cv2.imshow('SOBEL (DX, DY)', whole_edge)
cv2.waitKey(0)
cv2.destroyAllWindows()

