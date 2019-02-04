import * as React from "react";
import "./StationsSection.css";
import { IStationsSectionProps, IStationsSectionState } from "../../../types/StationTypes";
import { StationService } from "../../../services/StationService/StationService";
import { Map } from "../../Maps/Map";
import { MapService } from "../../../services/MapService/MapService";
import { CircleTooltip } from "../../Tooltips/CircleTooltip"; 
import { ICircleEvent } from "../../../types/MapTypes";
import { Link } from "react-router-dom";
import { Card, CardBody, CardTitle } from "reactstrap";
import { Nav, NavItem, NavLink } from "reactstrap";

export default class StationsSection extends React.Component<IStationsSectionProps, IStationsSectionState> {

    private mapHeight = 500;

    constructor(props: IStationsSectionProps) {
        super(props);

        this.loadStationsInfo = this.loadStationsInfo.bind(this);
        this.loadMapsInfo = this.loadMapsInfo.bind(this);
        this.mapHoveredStation = this.mapHoveredStation.bind(this);
        this.outputStationCards = this.outputStationCards.bind(this);

        this.state = {
            stations: [],
            maps: null,
            tooltipStation: undefined,
            tooltipX: 0,
            tooltipY: 0,
            tooltipActive: false,
            tooltipTextCallback: StationService.outputBartText
        };
    }

    public componentDidMount(): void {
        this.setState({
            stations: [],
            maps: null, 
            tooltipStation: undefined,
            tooltipX: 0, 
            tooltipY: 0,
            tooltipActive: false,
            tooltipTextCallback: StationService.outputBartText
        });
        this.loadStationsInfo();
    }

    private loadStationsInfo(): void {
        this.setState({stations: []});
        StationService.getStationsInfo().then(data => {
            this.setState({stations: data});
        });
    }

    private loadMapsInfo(): void {
        MapService.getMapData().then((mapData: any) => {
            this.setState({maps: mapData.data});
        });
    }

    private mapHoveredStation(position: ICircleEvent): void {
        const station = this.state.stations[parseInt(position.target.dataset.index)];
        
        this.setState({
            tooltipX: position.x,
            tooltipY: position.y,
            tooltipStation: station,
            tooltipActive: true,
            tooltipTextCallback: StationService.outputBartText
        });
    }

    private outputStationCards(): JSX.Element[] {
        return this.state.stations.map((station, index) => {
            return (
                <div className="col-xs-12 col-sm-3 station-card" key={"station-card-wrap" + index}>
                    <Card key={"station-card-" + index}>
                        <CardBody key={"station-body-" + index}>
                            <CardTitle key={"station-title-" + index}>{station.abbr}</CardTitle>
                            <div className="bart-text">
                                <div>{station.address}</div>
                                <div>{station.city}, {station.state} {station.zipcode}</div>
                            </div>
                            <Link   className="btn btn-secondary"
                                    key={"station-btn-" + index}
                                    to={"/station/" + station.abbr}>View</Link>
                        </CardBody>
                    </Card>
                </div>
            );
        });
    }

    public render(): JSX.Element {

        return (
            <section className="stations-section no-gutters">

                <div className="row">

                    <Nav vertical={true} className="col-xs-2">
                        <NavItem>
                            <NavLink href="#">Link</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink href="#">Link</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink href="#">Another Link</NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink disabled={true} href="#">Disabled Link</NavLink>
                        </NavItem>
                    </Nav>


                    <div className="col-xs-10">
                        <div className="card">
                            <Map    maps={this.state.maps} 
                                    stations={this.state.stations}
                                    hoverCallback={this.mapHoveredStation} />

                            <CircleTooltip  mapHeight={this.mapHeight}
                                            station={this.state.tooltipStation}
                                            tooltipActive={this.state.tooltipActive}
                                            text={this.state.tooltipTextCallback} />
                        </div>
                    </div>
                </div>
                <div className="row no-gutters">
                    {this.outputStationCards()}
                </div>
            </section>
        );

    }

}
