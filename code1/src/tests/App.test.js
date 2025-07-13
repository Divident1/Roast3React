import React from 'react';
import { render, fireEvent, waitFor, screen } from '@testing-library/react';
import LoanForm from '../LoanForm';
import { BrowserRouter as Router } from 'react-router-dom';

test('submitting valid form navigates to welcome page', async () => {
  render(
    <Router>
      <LoanForm />
    </Router>
  );

  fireEvent.change(screen.getByLabelText(/full name/i), { target: { value: 'John Doe' } });
  fireEvent.change(screen.getByLabelText(/loan amount/i), { target: { value: '5000' } });
  fireEvent.change(screen.getByLabelText(/tenure/i), { target: { value: '5' } });
  fireEvent.click(screen.getByText(/submit/i));
});

test('submitting invalid loan amount displays error message', async () => {
  render(
    <Router>
      <LoanForm />
    </Router>
  );

  fireEvent.change(screen.getByLabelText(/loan amount/i), { target: { value: '999' } });
  fireEvent.change(screen.getByLabelText(/full name/i), { target: { value: '' } });
  fireEvent.change(screen.getByLabelText(/tenure/i), { target: { value: '999' } });

  fireEvent.click(screen.getByText(/submit/i));
  

  await waitFor(() => expect(screen.getByText(/loan amount must be between 1000 and 1000000/i)).toBeInTheDocument());
  await waitFor(() => expect(screen.getByText(/Tenure must be between 1 and 30 years/i)).toBeInTheDocument());

  await waitFor(() => expect(screen.getByText(/Please enter your full name/i)).toBeInTheDocument());

});
