# 이진화

import cv2;

image = cv2.imread('image.jpg', -1)

roi = image[0:500, 100:500, :]
flipped_roi = cv2.flip(roi, 1)
gr_flipped_roi = flipped_roi[:, :, 1] # G 영역

# G 영역 이미지 확인
cv2.imshow('ORIGINAL', flipped_roi)
cv2.imshow('GREEN', gr_flipped_roi) # 어두울수록 초록 값이 큼
cv2.waitKey(0)
cv2.destroyAllWindows()

# 이진화
gr_bin_image = cv2.threshold(gr_flipped_roi, 100, 255, cv2.THRESH_BINARY) # 이미지 ndarry, 기준값, 최대값, 임계처리 타입
# print(gr_bin_image)
# # (200.0, array([[0, 0, 0, ..., 0, 0, 0],
# #        [0, 0, 0, ..., 0, 0, 0],
# #        [0, 0, 0, ..., 0, 0, 0],
# #        ...,
# #        [0, 0, 0, ..., 0, 0, 0],
# #        [0, 0, 0, ..., 0, 0, 0],
# #        [0, 0, 0, ..., 0, 0, 0]], dtype=uint8))
cv2.imshow('GREEN', gr_flipped_roi)
cv2.imshow('BINARY IMAGE', gr_bin_image[1])
cv2.waitKey(0)
cv2.destroyAllWindows()