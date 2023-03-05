package org.youngtak2.springboard.common.service;

public enum ServiceResult {
	SUCCESS, /* 성공 */
	FAILED, /* 실패 (데이터베이스 문제) */
	REJECTED /* 거부 (권한 없음 또는 기타 문제를 서비스가 발견하여 거부함) */
}