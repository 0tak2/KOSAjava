# 모폴로지 연산

import cv2;
import numpy as np;

image = cv2.imread('image.jpg', -1)

roi = image[0:500, 100:500, :]
flipped_roi = cv2.flip(roi, 1)
gr_flipped_roi = flipped_roi[:, :, 1] # G 영역

# 이진화
bin = cv2.threshold(gr_flipped_roi, 100, 255, cv2.THRESH_BINARY) # 이미지 ndarry, 기준값, 최대값, 임계처리 타입

# 모폴로지 연산
kernel = np.ones((5, 5), dtype=np.uint8)
erode_result = cv2.erode(bin[1], kernel)
dilate_result = cv2.dilate(bin[1], kernel)

# 출력
cv2.imshow('BINARY IMAGE', bin[1])
cv2.imshow('BINARY IMAGE EROSION', erode_result)
cv2.imshow('BINARY IMAGE DILATION', dilate_result)
cv2.waitKey(0)
cv2.destroyAllWindows()