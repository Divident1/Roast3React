import React from 'react';
import '@testing-library/jest-dom';
import { render, fireEvent, waitFor, screen } from '@testing-library/react';
import ShipmentForm from '../components/ShipmentForm';
import App from '../App';
test('Empty Form Submission', async () => {
  render(<ShipmentForm />);
  const submitButton = screen.getByText('Register Shipment');
  fireEvent.click(submitButton);
  await waitFor(() => {
    expect(screen.getByText('Sender is required')).toBeInTheDocument();
    expect(screen.getByText('Receiver is required')).toBeInTheDocument();
    expect(screen.getByText('Origin is required')).toBeInTheDocument();
    expect(screen.getByText('Destination is required')).toBeInTheDocument();
    expect(screen.getByText('Status is required')).toBeInTheDocument();
    expect(screen.getByText('Expected Delivery is required')).toBeInTheDocument();
  });
});

test('Invalid Sender and Receiver', async () => {
  render(<ShipmentForm />);
  const senderInput = screen.getByLabelText('Sender:');
  const receiverInput = screen.getByLabelText('Receiver:');
  fireEvent.change(senderInput, { target: { value: ' ' } });
  fireEvent.change(receiverInput, { target: { value: ' ' } });
  const submitButton = screen.getByText('Register Shipment');
  fireEvent.click(submitButton);
  await waitFor(() => {
    expect(screen.getByText('Sender is required')).toBeInTheDocument();
    expect(screen.getByText('Receiver is required')).toBeInTheDocument();
  });
});

test('Valid Form Submission', async () => {
  render(<ShipmentForm />);
  const senderInput = screen.getByLabelText('Sender:');
  const receiverInput = screen.getByLabelText('Receiver:');
  const originInput = screen.getByLabelText('Origin:');
  const destinationInput = screen.getByLabelText('Destination:');
  const statusInput = screen.getByLabelText('Status:');
  const expectedDeliveryInput = screen.getByLabelText('Expected Delivery:');
  
  fireEvent.change(senderInput, { target: { value: 'Tech Corp' } });
  fireEvent.change(receiverInput, { target: { value: 'John Doe' } });
  fireEvent.change(originInput, { target: { value: 'New York' } });
  fireEvent.change(destinationInput, { target: { value: 'San Francisco' } });
  fireEvent.change(statusInput, { target: { value: 'In Transit' } });
  fireEvent.change(expectedDeliveryInput, { target: { value: '2023-09-20' } });
  
  const submitButton = screen.getByText('Register Shipment');
  fireEvent.click(submitButton);
  
  await waitFor(() => {
    expect(screen.queryByText('Sender is required')).not.toBeInTheDocument();
    expect(screen.queryByText('Receiver is required')).not.toBeInTheDocument();
    expect(screen.queryByText('Origin is required')).not.toBeInTheDocument();
    expect(screen.queryByText('Destination is required')).not.toBeInTheDocument();
    expect(screen.queryByText('Status is required')).not.toBeInTheDocument();
    expect(screen.queryByText('Expected Delivery is required')).not.toBeInTheDocument();
  });
});