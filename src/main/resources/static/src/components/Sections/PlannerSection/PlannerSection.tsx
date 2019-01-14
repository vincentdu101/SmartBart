import * as React from "react";
import "./PlannerSection.scss";
import { IPlannerSectionState, IPlannerSectionProps } from "../../../types/PlannerTypes";
import PlannerTable from "../../PlannerTable/PlannerTable";
import DropdownInfo from "../../Dropdown/DropdownInfo";
import { StationService } from "../../../services/StationService/StationService";
import { Map } from "../../Maps/Map";

export default class PlannerSection extends React.Component<{}, IPlannerSectionState> {

    constructor(props: IPlannerSectionProps) {
        super(props);

        this.loadStationsInfo = this.loadStationsInfo.bind(this);
        this.originSelection = this.originSelection.bind(this);
        this.destinationSelection = this.destinationSelection.bind(this);

        this.state = {
            maps: null,
            stations: [],
            mapStations: [],
            mapSelectedStations: [],
            focusedStations: [],
            origin: "",
            destination: ""
        };
    }

    private loadStationsInfo(): void {
        this.setState({stations: []});
        StationService.getStationsInfo().then(data => {
            this.setState({stations: data, mapStations: data});
        });
    }

    private originSelection(origin: string): void {
        let selectedStation = StationService.filterForStation(origin, this.state.mapStations);
        let mapSelectedStations = this.state.mapSelectedStations.push(selectedStation);
        this.setState({origin: origin});
    }

    private destinationSelection(destination: string): void {
        this.setState({destination: destination});
    }

    public componentDidMount(): void {
        this.loadStationsInfo();
    }

    public render(): JSX.Element {
        return (
            <section className="planner-section">
                <div className="row planner-menu-row">
                    <div className="col-xs-6 first-col">
                        <DropdownInfo   input={this.state.stations}
                                        label="Origin" 
                                        selectionCallback={this.originSelection} />
                    </div>
                    <div className="col-xs-6">
                        <DropdownInfo   input={this.state.stations} 
                                        label="Destination"
                                        selectionCallback={this.destinationSelection} />
                    </div>
                </div>
                <PlannerTable   origin={this.state.origin} 
                                destination={this.state.destination} />

                <Map    maps={this.state.maps} 
                        stations={this.state.mapSelectedStations} />                                
            </section>
        );
    }

}