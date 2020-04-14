import React, { Component } from 'react';
import { Segment, Grid, Header, Button } from 'semantic-ui-react';
import moment from 'moment';

class FlightSegment extends Component {
  formatTime(time) {
    return moment(time).format('HH:mm');
  }

  render() {
    return (
      <Segment>
        <Grid style={{marginTop: '0px'}}>
          <Grid.Row>
            <Grid.Column width={4}><Header>{this.props.airline}</Header></Grid.Column>
            <Grid.Column width={4}><Header as='h2'>{this.props.destFrom}</Header></Grid.Column>
            <Grid.Column width={4}><Header as='h2'>{this.props.destTo}</Header></Grid.Column>
            <Grid.Column width={4} textAlign='right'><Header>{this.props.price.toLocaleString()} kr.</Header></Grid.Column>
          </Grid.Row>
          <Grid.Row>
            <Grid.Column width={4}><Header>{this.props.flightNumber}</Header></Grid.Column>
            <Grid.Column width={4}><Header>{this.formatTime(this.props.departureTime)}</Header></Grid.Column>
            <Grid.Column width={4}><Header>{this.formatTime(this.props.arrivalTime)}</Header></Grid.Column>
            <Grid.Column width={4} textAlign='right'><b>{this.props.seats.length} sæti laus</b></Grid.Column>
          </Grid.Row>
          <Grid.Row>
            <Grid.Column width={16} textAlign='right'>
              { this.props.bookable && <Button onClick={() => this.props.bookFunction(this.props.flightNumber)}>Bóka</Button> }
            </Grid.Column>
          </Grid.Row>
        </Grid>
      </Segment>
    );
  }
}

export default FlightSegment;
