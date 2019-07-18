import * as React from "react";
import "./ActivityFeed.css";
import {IEtd, IEstimate} from "../../types/StationTypes";
import {IActivityFeedProps, IActivityFeedState} from "../../types/ActivityTypes";
import {Card, Collapse, ListGroup, ListGroupItem, Badge } from "reactstrap";

export default class ActivityFeed extends React.Component<IActivityFeedProps, IActivityFeedState> {

    constructor(props: IActivityFeedProps) {
        super(props);

        this.toggleCollapse = this.toggleCollapse.bind(this);
        this.generateBikeBadge = this.generateBikeBadge.bind(this);
        this.generateLimitedClass = this.generateLimitedClass.bind(this);

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

    private generateLimitedClass(limited: number): JSX.Element {
        if (limited === 1) {
            return <Badge color="warning">Limited</Badge>;
        } else {
            return <Badge color="secondary">Normal</Badge>;
        }
    }

    private generateBikeBadge(bikeFlag: boolean): JSX.Element {
        if (bikeFlag) {
            return <span className="badge badge-primary">Yes</span>;
        } else {
            return <span className="badge badge-danger">No</span>;
        }
    }

    private toggleCollapse(event: any): void {
        let collapse = this.state.collapse;
        collapse[event.target.dataset.abbr] = !collapse[event.target.dataset.abbr];
        this.setState({collapse: collapse});
    }

    private generateEstimates(estimates: IEstimate[]): JSX.Element[] {
        return estimates.map((estimate: IEstimate, index) => {
            return (
                <Card key={"estimate-card" + index}>
                    <div key={"estimate-card-body" + index} className="station-row-info">
                        <table className="table">
                            <tbody>
                                <tr><td className="station-col-left">Bike</td><td>{this.generateBikeBadge(estimate.bikeflag)}</td></tr>
                                <tr><td className="station-col-left">Minutes Delayed</td><td>{estimate.delay}</td></tr>
                                <tr><td className="station-col-left">Direction</td><td>{estimate.direction}</td></tr>
                                <tr><td className="station-col-left">Length of Train</td><td>{estimate.length}</td></tr>
                                <tr><td className="station-col-left">Minutes Before Arrival</td><td>{estimate.minutes}</td></tr>
                                <tr><td className="station-col-left">Platform Number</td><td>{estimate.platform}</td></tr>
                            </tbody>
                        </table>
                    </div>
                </Card>
            );
        });
    }

    private generateActivityRows(rows: IEtd[]): JSX.Element[] {
        return rows.map((etd: IEtd, index: number) => {
            return (
                <div key={"section" + index}>
                    <ListGroupItem key={"est" + index}  
                                id="toggler" 
                                data-abbr={etd.abbreviation}
                                onClick={this.toggleCollapse}>
                            {etd.abbreviation} - {etd.destination} {this.generateLimitedClass(etd.limited)}
                    </ListGroupItem>
                    <Collapse isOpen={!!this.state.collapse[etd.abbreviation]}>
                        {this.generateEstimates(etd.estimates)}
                    </Collapse>
                </div>
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