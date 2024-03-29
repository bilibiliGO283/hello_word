package com.org.Exam;

import java.util.List;

public interface ExamDao {
	void insertExam(Exam s1);
	void updateExam(Exam s1);
	void deleteExam(String no1);
	Exam getExamByNo(String no1);
	
	
	
	int getExamnum();
	List<String> getExamname();
	List<String> getExamtst_no();
	List<String> getExamname_tst_no();
	List<String> getExampro_Choice(String tst_no);
	List<String> getExamShort_Answer(String tst_no);
	
	List<Answer_table> getExamanstable(String tst_no);
	
	String getAnsbyNo(String no1);
	int getScorebyno(String no1);
	void ansSaved(String tst_no,String no1,String ans,String score);
	
}
