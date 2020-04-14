import React from 'react';
import { Container } from 'semantic-ui-react';
import { BrowserRouter as Router, Route } from 'react-router-dom';

import Navbar from './Navbar';

import FlightSearch from './FlightSearch';
import FlightDetails from './FlightDetails';
import BookingDetails from './BookingDetails';

function App() {
  return (
    <div className="App">
      <Router>
        <Navbar />
        <Container style={{marginTop: '60px'}}>
          <Route exact path='/' component={FlightSearch} />
          <Route exact path='/flight' component={FlightDetails} />
          <Route exact path='/booking' component={BookingDetails} />
        </Container>
      </Router>
    </div>
  );
}

export default App;
