use sqldb2;

-- 1
select job as '직무', truncate(avg(sal), -2) as '급여 평균' from (select job, sal from emp where DEPTNO = 30) empOf30 group by job;

-- 2
select D.DNAME as '부서명', E.COUNT as '직원 수'
from (select DEPTNO, count(JOB) as count from emp group by DEPTNO) E
	inner join DEPT D
	on e.deptno = d.DEPTNO
where E.count >= 4;

-- 3
select JOB as '직무명', format(sum(SAL), 0) as '급여의 합' from emp group by JOB
union
select 'TOTAL', format(sum(SAL), 0) from emp
order by 1;

-- 4
select E.ENAME, E.SAL, S.GRADE
from (select ENAME, SAL from emp where SAL = (select max(SAL) from emp)) E
inner join salgrade S
on E.SAL >= S.LOSAL and e.SAL < s.HISAL;

-- 5
select ENAME as '직원명', concat(SAL + COMM, '원') as '급여'
from
	(select ENAME, SAL, ifnull(COMM, 0) as COMM, HIREDATE from emp) e2
where year(HIREDATE) = 1981
order by '급여' desc;

select ENAME as '직원명', concat(SAL + COMM, '원') as '급여'
from
(select ENAME, SAL, COMM
from
	(select ENAME, SAL, ifnull(COMM, 0) as COMM, HIREDATE from emp) e2
where year(HIREDATE) = 1981
order by SAL desc) e3;

-- 6
select ENAME as '직원명', concat(year(HIREDATE), '년 ', month(HIREDATE), '월 ', day(HIREDATE), '일') as '입사년월일', 'A' as '그룹'
from emp
where year(HIREDATE) = 1980
union
select ENAME, concat(year(HIREDATE), '년 ', month(HIREDATE), '월 ', day(HIREDATE), '일'), 'B'
from emp
where year(HIREDATE) = 1981
union
select ENAME, concat(year(HIREDATE), '년 ', month(HIREDATE), '월 ', day(HIREDATE), '일'), 'C'
from emp
where year(HIREDATE) = 1982
union
select ENAME, concat(year(HIREDATE), '년 ', month(HIREDATE), '월 ', day(HIREDATE), '일'), 'D'
from emp
where year(HIREDATE) = 1983;

-- 7
select e1.EMPNO as'사원 사번', e1.ENAME '사원 이름', e1.MGR as '관리자 사번', e2.ENAME as '관리자 이름' 
from emp e1
	inner join emp e2
	on e1.MGR = e2.EMPNO;
	
-- 8
select e.EMPNO, e.ENAME, e.DEPTNO
from emp e
inner join
(select DEPTNO
from dept d inner join locations l on d.LOC_CODE = l.LOC_CODE 
where d.LOC_CODE = (select LOC_CODE from locations where CITY = 'CHICAGO')) d
on e.DEPTNO = d.DEPTNO

-- 9
select ename, sal
from emp
where sal > (select max(SAL) from emp where DEPTNO = 30);