package ch.protonmail.vladyslavbond.learn.datasource;

import ch.protonmail.vladyslavbond.learn.domain.Exam;
import ch.protonmail.vladyslavbond.learn.domain.DescriptorFactory;
import ch.protonmail.vladyslavbond.learn.domain.ExamDescriptor;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

enum NativeExamDataAccess implements ExamDataAccess
{
	INSTANCE ( );

	private NativeExamDataAccess ( )
	{

	}

	@Override
	public List<Exam> read ( )
	throws DataAccessException
	{
		try
		{
			ResultSet resultSet = Query.newInstance("SELECT * FROM exam_read ( );")
				.executeQuery( );
			List<Exam> listOfExams = new ArrayList<Exam> ( );
			while (resultSet.next( ))
			{
				ExamDescriptor examDescriptor = DescriptorFactory.newExamDescriptor(resultSet);
				listOfExams.add((Exam)examDescriptor.newInstance( ));
			}
			return listOfExams;
		} catch (SQLException e) {
			throw new DataAccessException (e);
		}
	}
}