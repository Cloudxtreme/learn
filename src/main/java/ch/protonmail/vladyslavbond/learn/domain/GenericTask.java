package ch.protonmail.vladyslavbond.learn.domain;

import java.util.List;

interface GenericTask<T, S, U>
{
	public abstract T getDescription ( );

	public abstract void answer (S givenAnswer);

	public abstract U getAnswer ( );
}