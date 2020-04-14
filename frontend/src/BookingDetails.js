import React, { Component } from 'react';
import { Container, Grid, Segment, Header, Form, Input, Button, Select } from 'semantic-ui-react';
import axios from 'axios';
import moment from 'moment';

class Booking extends Component {
  constructor(props) {
    super(props);

    this.state= {
      flight: {destTo: null, destFrom: null, departureTime: null, arrivalTime: null, airline: null}
    }
  }

  componentDidMount() {
    var that=this;
    axios.get("/api/flight?flightNumber="+this.props.flightNumber).then(function(response) {
      that.setState({flight: response.data});
    });
  }

  render() {
    return (
        <Segment>
          <Grid>
            <Grid.Row>
              <Grid.Column width={6}>
                <Header>{this.state.flight.airline}</Header>
                <Header>Farnúmer {this.props.bookingNumber}</Header>
                <p>{this.props.passenger.name}</p>
                <p>{this.props.passenger.email}</p>
                <p>{this.props.passenger.phoneNumber}</p>
              </Grid.Column>
              <Grid.Column width={5}>
                <Header>Flug {this.props.flightNumber}</Header>
                <Header as='h2'>{this.state.flight.destFrom}</Header>
                <Header>{moment(this.state.flight.departureTime).format("MMMM D YYYY, HH:mm")}</Header>
              </Grid.Column>
              <Grid.Column width={5}>
                <Header>Sæti {this.props.seat}</Header>
                <Header as='h2'>{this.state.flight.destTo}</Header>
                <Header>{moment(this.state.flight.arrivalTime).format("MMMM D YYYY, HH:mm")}</Header>
                { this.props.luggage ? <p>Með farangri</p> : <p> Án farangurs</p> }
              </Grid.Column>
            </Grid.Row>
          </Grid>
        </Segment>
      );
  }
}

class BookingDetails extends Component {
  constructor(props) {
    super(props);

    this.state = {
      bookingNumber: '',
      result: null
    };
  }

  handleChange = (e, { name, value }) => this.setState({ [name]: value })

  search() {
    var url = "/api/booking?bookingNumber="+this.state.bookingNumber;

    axios.get(url).then(response => {
        console.log(response.data);
        this.setState({result: response.data});
    });
  }

  render() {
    return (
      <div>
        <Segment>
          <Header>Bókunin mín</Header>
          <Form>
            <Form.Group>
              <Form.Field
                name='bookingNumber'
                control={Input}
                label='Bókunarnúmer'
                placeholder='Bókunarnúmer'
                value={this.state.bookingNumber}
                onChange={this.handleChange}
                width={16}
              />
            </Form.Group>
            <Form.Field primary control={Button} onClick={() => this.search()}>Leita</Form.Field>
          </Form>
        </Segment>
        { this.state.result && <div>
        <Header dividing>Bókunarupplýsingar</Header>
        <Header as='h1'>Bókunarnúmer {this.state.result.groupBookingNumber}</Header>
        {this.state.result.bookings.map(x => <Booking {...x} />)}
        </div> }
      </div>
    );
  }
}

export default BookingDetails;
