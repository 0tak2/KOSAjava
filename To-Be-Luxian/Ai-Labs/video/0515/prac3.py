# 가우시안 블러링

import cv2;
import numpy as np;

image = cv2.imread('image.jpg', -1)

roi = image[0:500, 100:500, :]
flipped_roi = cv2.flip(roi, 1)
gr_flipped_roi = flipped_roi[:, :, 1] # G 영역

# 이진화
bin = cv2.threshold(gr_flipped_roi, 100, 255, cv2.THRESH_BINARY) # 이미지 ndarry, 기준값, 최대값, 임계처리 타입

# 가우시안 블러링
kernel = np.ones((5, 5), dtype=np.uint8)
blur_result_5 = cv2.GaussianBlur(bin[1], (5, 5), 3);
blur_result_25 = cv2.GaussianBlur(bin[1], (25, 25), 3);

# 출력하여 확인
cv2.imshow('BINARY IMAGE', bin[1])
cv2.imshow('GAUSSIAN BLUR (Kernel: 5 * 5)', blur_result_5)
cv2.imshow('GAUSSIAN BLUR (Kernel: 25 * 25)', blur_result_25)
cv2.waitKey(0)
cv2.destroyAllWindows()

