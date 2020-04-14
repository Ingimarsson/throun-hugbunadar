import React, { Component } from 'react';
import { Container, Segment, Header, Form, Input, Button, Select } from 'semantic-ui-react';
import { DateInput } from 'semantic-ui-calendar-react';
import axios from 'axios';
import moment from 'moment';

import FlightSegment from './FlightSegment';

const results = [
  {flightNumber:"NY153",airline:"Air Iceland Connect",destTo:"AEY",destFrom:"RVK",departureTime:"2019-03-28 14:00:00",arrivalTime:"2019-03-28 14:50:00", price:14500},
  {flightNumber:"NY155",airline:"Air Iceland Connect",destTo:"AEY",destFrom:"RVK",departureTime:"2019-03-28 16:30:00",arrivalTime:"2019-03-28 17:20:00", price:18500},
]

class FlightSearch extends Component {
  constructor(props) {
    super(props);

    this.state = {
      destFrom: 'RKV',
      destTo: 'AEY',
      date: '',
      passengers: 1,
      results: []
    };
  }

  handleChange = (e, { name, value }) => this.setState({ [name]: value })

  search() {
    var url = "/api/search?to="+this.state.destTo+"&from="+this.state.destFrom+"&date="+moment(this.state.date, "DD-MM-YYYY").format("YYYY-MM-DD");

    axios.get(url).then(response => {
        console.log(response.data);
        this.setState({results: response.data});
    });
  }

  render() {
    const destinations = [
      {key: 'RKV', text: 'Reykjavík (RKV)', value: 'RKV'},
      {key: 'AEY', text: 'Akureyri (AEY)', value: 'AEY'},
      {key: 'EGS', text: 'Egilsstaðir (EGS)', value: 'EGS'},
      {key: 'IFJ', text: 'Ísafjörður (IFJ)', value: 'IFJ'},
      {key: 'THO', text: 'Þórshöfn (THO)', value: 'THO'},
    ]

    return (
      <div>
        <Segment>
          <Header>Hvert viltu fljúga?</Header>
          <Form>
            <Form.Group>
              <Form.Field
                name='destFrom'
                control={Select}
                label='Brottfararstaður'
                placeholder='Brottfararstaður'
                options={destinations}
                value={this.state.destFrom}
                onChange={this.handleChange}
                width={5}
              />
              <Form.Field
                name='destTo'
                control={Select}
                label='Áfangastaður'
                placeholder='Áfangastaður'
                options={destinations}
                value={this.state.destTo}
                onChange={this.handleChange}
                width={5}
              />
              <Form.Field
                name='date'
                control={DateInput}
                animation='none'
                label='Dagsetning'
                placeholder='Dagsetning'
                value={this.state.date}
                onChange={this.handleChange}
                width={4}
              />
              <Form.Field
                name='passengers'
                control={Input}
                label='Farþegar'
                placeholder='Farþegar'
                value={this.state.passengers}
                onChange={this.handleChange}
                type='number'
                width={2}
              />
            </Form.Group>
            <Form.Field primary control={Button} onClick={() => this.search()}>Leita</Form.Field>
          </Form>
        </Segment>
        <Header dividing>Leitarniðurstöður</Header>
        {this.state.results.map(function(x) { return <FlightSegment {...x} />})}
      </div>
    );
  }
}

export default FlightSearch;
