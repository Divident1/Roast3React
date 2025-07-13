import React from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import LoanForm from './LoanForm';
import WelcomePage from './WelcomePage';
import ErrorPage from './ErrorPage';

const App = () => {
  return (
    <Router>
      <Routes>
        <Route  path="/" element={<LoanForm/>} />
        <Route path="/welcome" element={<WelcomePage/>} />
        <Route path="/error" element={<ErrorPage/>} />
      </Routes>
    </Router>
  );
};

export default App;
