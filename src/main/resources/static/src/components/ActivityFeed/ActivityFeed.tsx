import * as React from "react";
import "./ActivityFeed.css";
import {IEtd, IEstimate} from "../../types/StationTypes";
import {IActivityFeedProps, IActivityFeedState} from "../../types/ActivityTypes";
import {Card, Collapse, ListGroup, ListGroupItem } from "reactstrap";

export default class ActivityFeed extends React.Component<IActivityFeedProps, IActivityFeedState> {

    constructor(props: IActivityFeedProps) {
        super(props);

        this.toggleCollapse = this.toggleCollapse.bind(this);

        this.state = {
            feed: [],
            collapse: {}
        };
    }

    componentDidMount(): void {
        this.setState({});
    }

    componentWillReceiveProps(nextProps: Readonly<IActivityFeedProps>): void {
        if (nextProps.feed) {
            this.setState({feed: nextProps.feed});
        }
    } 

    private toggleCollapse(event: any): void {
        let collapse = this.state.collapse;
        collapse[event.target.dataset.abbr] = !collapse[event.target.dataset.abbr];
        this.setState({collapse: collapse});
    }

    generateEstimates(estimates: IEstimate[]): JSX.Element[] {
        return estimates.map((estimate: IEstimate, index) => {
            return (
                <Card key={"estimate-card" + index}>
                    <div key={"estimate-card-body" + index} className="station-row-info">
                        <div key={"estimate-bike" + index}>Bike: {estimate.bikeflag}</div>
                        <div key={"estimate-delay" + index}>Minutes Delayed: {estimate.delay}</div>
                        <div key={"estimate-direction" + index}>Direction: {estimate.direction}</div>
                        <div key={"estimate-length" + index}>Length of Train: {estimate.length}</div>
                        <div key={"estimate-minutes" + index}>Minutes before Arrival: {estimate.minutes}</div>
                        <div key={"estimate-platform" + index}>Platform: {estimate.platform}</div>
                    </div>
                </Card>
            );
        });
    }

    generateActivityRows(rows: IEtd[]): JSX.Element[] {
        return rows.map((etd: IEtd, index: number) => {
            return (
                <ListGroupItem key={"est" + index}  
                            id="toggler" 
                            data-abbr={etd.abbreviation}
                            style={{ marginBottom: '1rem' }}
                            onClick={this.toggleCollapse}>
                        {etd.abbreviation} - {etd.destination}
                    <Collapse isOpen={!!this.state.collapse[etd.abbreviation]}>
                        {this.generateEstimates(etd.estimates)}
                    </Collapse>
                </ListGroupItem>
            );            
        });
    }

    render(): JSX.Element {
        return (
            <ListGroup className="activity-feed">
                {this.generateActivityRows(this.state.feed)}
            </ListGroup>
        );
    }


}