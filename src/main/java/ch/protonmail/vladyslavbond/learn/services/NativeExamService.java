package ch.protonmail.vladyslavbond.learn.services;

import ch.protonmail.vladyslavbond.learn.datasource.ExamDataAccess;
import ch.protonmail.vladyslavbond.learn.datasource.DataAccessProvider;

import ch.protonmail.vladyslavbond.learn.domain.Exam;

import java.util.List;

enum NativeExamService implements ExamService
{
	INSTANCE ( );

	private NativeExamService ( )
	{

	}

	private ExamDataAccess getExamDataAccess ( )
	{
		return DataAccessProvider.getExamDataAccess( );
	}

	@Override
	public List<Exam> read ( )
	throws ExamServiceException
	{
		try
		{
			return this.getExamDataAccess( ).read( );
		} catch (Exception e) {
			throw new ExamServiceException ("Failed get list of exams.", e);
		}
	}
}